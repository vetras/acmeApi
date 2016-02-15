package resource;

import spark.Spark;

public class Healthz extends Resource {

    public Healthz(String BaseUrl) {
        super(BaseUrl);
    }

    @Override
    public Resource RegisterActions() {
        Spark.get(BaseUrl + "/health", (req, res) -> "It is Alive !");
        return this;
    }
}
