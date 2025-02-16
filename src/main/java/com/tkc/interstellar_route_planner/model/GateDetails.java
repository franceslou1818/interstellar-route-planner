package com.tkc.interstellar_route_planner.model;

import java.util.List;

public class GateDetails {

    private String id;
    private String name;
    private List<Connection> connections;

    public GateDetails() {}

    public GateDetails(String id, String name, List<Connection> connections) {
        this.id = id;
        this.name = name;
        this.connections = connections;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Connection> getConnections() {
        return connections;
    }
    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public class Connection {

        private String id;
        private int hu;

        public Connection() {}

        public Connection(String id, int hu) {
            this.id = id;
            this.hu = hu;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public int getHu() {
            return hu;
        }
        public void setHu(int hu) {
            this.hu = hu;
        }

    }
}
