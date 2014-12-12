package citycode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler{
    private ArrayList<TagBean> tagList = null;  
    private TagBean tag = null;
    private Stack<String> tagNameStack = null;//标记当前在处理的tag的隶属关系，遇到<>时入栈，遇到 </>时出栈
    private Stack<TagBean> tagBeanStack = null;
    private TagBean rootTag = null;
      
    public TagBean getRootTag(InputStream xmlStream) throws Exception{  
        //获取sax分析器
        SAXParserFactory factory = SAXParserFactory.newInstance();  
        SAXParser parser = factory.newSAXParser();
        //根据具体程序要求进行分析
        MyHandler handler = new MyHandler();  
        parser.parse(xmlStream, handler);  //给分析器传入文件路径和分析规则
        return handler.getRootTag(); //分析结束，获得信息 
    }  
      
    public ArrayList<TagBean> getTagList(){  
        return tagList;
    }  
      
    @Override  //当文件解析开始时，会调用此函数
    public void startDocument() throws SAXException {  
        tagList = new ArrayList<TagBean>();
        tagNameStack = new Stack<String>();
        tagBeanStack = new Stack<TagBean>();
    }  
  
    @Override  //当碰到ElementNode时（如<books>），会调用此方法
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
        tagNameStack.push(qName);
        tag = new TagBean();
        tag.setName(qName);
        
        //存储标签的属性信息
        int index = attributes.getLength();
        if(index > 0){
            Map<String,String> attrList = new HashMap<String,String>();
            for(int i = 0 ; i < index ; i++ ){
                attrList.put(attributes.getQName(i),attributes.getValue(i));
            }
            tag.setAttrList(attrList);
        }
        
        //将正在解析的节点名称赋给preTag
        //preTag = qName;
        
        //处理子标签问题
        tagBeanStack.push(tag);
        
        //保存beans的引用
        if("world".equals(qName)){
            rootTag = tag;
        }
    }  
  
    @Override  //当遇到</>时调用此方法
    public void endElement(String uri, String localName, String qName)  throws SAXException {  
        //System.out.println(qName+" end");
        
        //处理了</>后，需要弹栈，并且构建子标签与父标签的关系
        tagNameStack.pop();
        tag = tagBeanStack.pop();
        if(!tag.getName().equals("world")){
            tagBeanStack.peek().setChildList(tag);
        }
        
        //preTag = null;
    }  
      
    @Override  //当遇到TextNode时调用，比如空格、值
    public void characters(char[] ch, int start, int length) throws SAXException {  
        //System.out.println("char here");
        String content = new String(ch,start,length);  
        content = content.trim();
        
        if(content.length() > 0){
            tagNameStack.peek();
            tag = tagBeanStack.peek();
            tag.getAttrList().put(tagNameStack.peek(), content);
        }
        
    }
    
    public TagBean getRootTag(){
        return this.rootTag;
    }
    
    
    public static void main(String[] args) throws IOException{
    	 MyHandler sax;  
         InputStream input = null;
         try {
            sax = new MyHandler();
            input = sax.getClass().getClassLoader().getResourceAsStream("world.xml");
            //解析input的xml文件，存储成树结构的tag链
            TagBean rootTag = sax.getRootTag(input);
            TagBean asia = rootTag.getChildList().get(0);
            TagBean china = asia.getChildList().get(11);
//            System.out.println(china.getChildList().size());
            Map<String,String> codes = new HashMap<String,String>(4096,1);
            for(TagBean tag : china.getChildList()){
            	getOldAndNew(codes, tag);
            }
            System.out.println(codes.size());
            GetOldCodeFromRedis.put(codes);
            //输出解析后存储的标签树结构
//          rootTag.print(0);
//            rootTag.generateJSON(0, 3);
//            rootTag.generateJSON(0, 4);
//            rootTag.generateJSON(0, 5);
//            rootTag.generateJSON(0, 6);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            input.close();
        }
    }
    
    private static void getOldAndNew(Map<String,String> map,TagBean tag){
    	String old = tag.getAttrList().get("code");
    	String cityId = tag.getAttrList().get("cityId");
    	if(!StringUtils.isEmpty(old)){
    		System.out.println(old+":"+cityId);
    		map.put(old, cityId);
    	}
    	if(tag.getChildList().size()>0){
    		for(TagBean tb : tag.getChildList()){
    			getOldAndNew(map,tb);
    		}
    	}
    }
}