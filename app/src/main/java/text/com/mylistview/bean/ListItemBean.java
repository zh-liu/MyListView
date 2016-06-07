package text.com.mylistview.bean;

import java.io.Serializable;

/**
 * Created by liuzh on 2016/6/1.
 */
public class ListItemBean implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String url;
}
