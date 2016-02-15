package resource;

public abstract class Resource {

    protected final String BaseUrl;

    public Resource(String BaseUrl) {
        this.BaseUrl = BaseUrl;
    }
    
    public Resource RegisterActions(){
        return this;
    }

}
