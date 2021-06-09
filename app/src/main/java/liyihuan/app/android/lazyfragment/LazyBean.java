package liyihuan.app.android.lazyfragment;

/**
 * @ClassName: ItemBean
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/8 21:58
 */
public class LazyBean {
    public LazyBean(String textContent) {
        this.textContent = textContent;
    }

    private String textContent;

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
