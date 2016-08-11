package BusInfo;

import java.util.List;

/**
 * Created by Burhan N on 8/7/2016.
 */
public class BusInfo {
    private long currentTime;
    private Data data;

    public BusInfo(long currentTime, Data data){
        this.currentTime = currentTime;
        this.data = data;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public Data getData() {
        return data;
    }

    public class Data {
        private Entry entry;
        private References references;


        public Data(Entry entry, References references){
            this.entry = entry;
            this.references = references;
        }

        public Entry getEntry() {
            return entry;
        }

        public References getReferences() {
            return references;
        }

        public class Entry {
            private List<Entry.StopRoute> stopRouteSchedules;

            public Entry (List<Entry.StopRoute> stopRouteSchedules){
                this.stopRouteSchedules = stopRouteSchedules;
            }

            public List<Entry.StopRoute> getStopRouteSchedules() {
                return stopRouteSchedules;
            }

            public class StopRoute {
                private long routeID;
                private List<Entry.StopRoute.StopRouteDirect> stopRouteDirectionSchedules;

                public StopRoute(long routeID, List<Entry.StopRoute.StopRouteDirect> stopRouteDirectionSchedules){
                    this.routeID = routeID;
                    this.stopRouteDirectionSchedules = stopRouteDirectionSchedules;
                }

                public long getRouteID() {
                    return routeID;
                }

                public List<Entry.StopRoute.StopRouteDirect> getStopRouteDirectionSchedules() {
                    return stopRouteDirectionSchedules;
                }

                public class StopRouteDirect {
                    private List<Entry.StopRoute.StopRouteDirect.ScheduleStopTime> scheduleStopTimes;

                    public StopRouteDirect (List<Entry.StopRoute.StopRouteDirect.ScheduleStopTime> scheduleStopTimes){
                        this.scheduleStopTimes = scheduleStopTimes;
                    }

                    public List<Entry.StopRoute.StopRouteDirect.ScheduleStopTime> getScheduleStopTimes() {
                        return scheduleStopTimes;
                    }

                    public class ScheduleStopTime {
                        private long arrivalTime;
                        private long departureTime;
                        private String serviceId;
                        private String tripId;

                        public ScheduleStopTime(long arrivalTime, long departureTime, String serviceId, String tripId){
                            this.arrivalTime = arrivalTime;
                            this.departureTime = departureTime;
                            this.serviceId = serviceId;
                            this.tripId = tripId;
                        }

                        public long getArrivalTime() {
                            return arrivalTime;
                        }

                        public long getDepartureTime() {
                            return departureTime;
                        }

                        public String getServiceId() {
                            return serviceId;
                        }

                        public String getTripId() {
                            return tripId;
                        }
                    }
                }
            }
        }

        public class References {
            private List<Route> routes;

            public References(List<Route> routes){
                this.routes = routes;
            }

            public List<Route> getRoutes(){
                return routes;
            }

            public class Route {
                private long angencyId;
                private String description;
                private long id;
                private long shortName;
                private long type;

                public Route(long angencyId, String description, long id, long shortName, long type){
                    this.angencyId = angencyId;
                    this.description = description;
                    this.id = id;
                    this.shortName = shortName;
                    this.type = type;
                }

                public long getAngencyId() {
                    return angencyId;
                }

                public String getDescription() {
                    return description;
                }

                public long getId() {
                    return id;
                }

                public long getShortName() {
                    return shortName;
                }

                public long getType() {
                    return type;
                }
            }
        }
    }
}
