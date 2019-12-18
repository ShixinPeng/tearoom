package stack;


/**
 * @author shixinpeng
 * @description 模拟浏览器前进后退
 * @ClassName: WebGoBack
 * @date 2019/12/12
 *
 */
public class WebGoBack {
    LinkedStack historyStack = new LinkedStack();
    LinkedStack preStack  = new LinkedStack();

    /**
     * 开新网页
     * @param url
     */
    public void openNewUrl(String url){
        System.out.println("open:"+url);
        historyStack.push(url);
        preStack.clear();
    }

    /**
     * 回退
     */
    public void goback(){
        String history = historyStack.pop();
        if (null != history){
            System.out.println("goback:" + history);
            preStack.push(history);
        }else {
            System.out.println("history is empty!");
        }

    }

    /**
     * 历史中前进
     */
    public void gopre(){
        String pre = preStack.pop();
        if (null != pre){
            System.out.println("goPre:" + pre);
            historyStack.push(pre);
        }else {
            System.out.println("current is best new!");
        }
    }

    public static void main(String[] args) {
        // 进入淘宝taobao
        WebGoBack web = new WebGoBack();
        web.openNewUrl("taobao");
        web.openNewUrl("tianbao");
        web.openNewUrl("163");
        web.openNewUrl("qq");
        web.openNewUrl("didi");

        web.goback();
        web.goback();
        web.gopre();
        web.gopre();
        web.gopre();

        web.goback();
        web.openNewUrl("baidu");
        web.gopre();
        web.goback();



    }

}
