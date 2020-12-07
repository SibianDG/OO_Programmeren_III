package domein;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

//TODO
public class Verzoek <T extends Serializable> /*!!*/ implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String parameter;
    private final String query;
    //TODO
    private List<T> antwoordLijst;

    public Verzoek(String query, String text) {
        this.query=query;
        parameter=text;
    }

    public String getParameter() {
        return parameter;
    }
    public String getQuery() {
        return query;
    }

    //TODO
    public List<T> getResultaat() {
        return Collections.unmodifiableList(antwoordLijst);
    }
    //TODO
    public void setResult(List<T> antwoordLijst) {
        this.antwoordLijst = antwoordLijst;
    }
}
