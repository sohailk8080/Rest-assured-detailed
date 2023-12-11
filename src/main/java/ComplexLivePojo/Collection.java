package ComplexLivePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Collection {

    //PMAK-638c7bd1059954005236efde-790498a5968d1527bb47af5e31870d4f66
    CollectionRoot collection;

    public Collection() {
    }

    public Collection(CollectionRoot collection) {
        this.collection = collection;
    }

    public CollectionRoot getCollection() {
        return collection;
    }

    public void setCollection(CollectionRoot collection) {
        this.collection = collection;
    }

}
