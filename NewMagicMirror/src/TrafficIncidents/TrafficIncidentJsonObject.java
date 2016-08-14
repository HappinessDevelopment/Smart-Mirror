package TrafficIncidents;

import java.util.List;

/**
 * Created by Burhan N on 8/13/2016.
 */
public class TrafficIncidentJsonObject {
    private List<ResourceSets> resourceSets;

    public TrafficIncidentJsonObject(List<ResourceSets> resourceSets){
        this.resourceSets = resourceSets;
    }

    public List<ResourceSets> getResourceSets() {
        return resourceSets;
    }

    public class ResourceSets {
        private int estimatedTotal;
        private List<Resources> resources;

        public ResourceSets(int estimatedTotal, List<Resources> resources) {
            this.estimatedTotal = estimatedTotal;
            this.resources = resources;
        }

        public int getEstimatedTotal() {
            return estimatedTotal;
        }

        public List<Resources> getResources() {
            return resources;
        }

        public class Resources{
            private String description;
            private String end;
            private boolean roadClosed;
            private int severity;
            private String start;
            private int type;

            private Resources(String description, String end, boolean roadClosed, int severity, String start, int type) {
                this.description = description;
                this.end = end;
                this.roadClosed = roadClosed;
                this.severity = severity;
                this.start = start;
                this.type = type;
            }

            public String getDescription() {
                return description;
            }

            public String getEnd(){
                return end;
            }

            public boolean isRoadClosed() {
                return roadClosed;
            }

            public int getSeverity() {
                return severity;
            }

            public String getStart() {
                return start;
            }

            public int getType() {
                return type;
            }
        }
    }
}
