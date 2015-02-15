package y1j2x34.wechat.pojo;
/** 
 * 复杂按钮（父按钮） 
 * 用于创建接口
 * @author 杨建新
 * @date 2013-12-26 
 */  
public class ComplexButton extends Button {  
    private Button[] sub_button;  
  
    public Button[] getSub_button() {  
        return sub_button;  
    }  
  
    public void setSub_button(Button[] sub_button) {  
        this.sub_button = sub_button;  
    }  
}  