package BusInfo;

import java.util.List;

/**
 * @author Magic Mirror Group
 * @version 2
 *
 * Class is used for parsing the json of the bus information using the Gson method where we use classes and variables for assigning the different variables.
 */
public class BusInfo {
    // The current time in milliseconds
    private long currentTime;
    // Another object inside the BusInfo class that holds the Entries and References.
    private Data data;

    /**
     * Default constructor used for assigning the information to the instance variables.
     * @param currentTime The current time in milliseconds.
     * @param data Another object inside BusInfo
     */
    public BusInfo(long currentTime, Data data){
        this.currentTime = currentTime;
        this.data = data;
    }

    /**
     * Method reutns the current time in milliseconds.
     * @return Returning long representing the milliseconds.
     */
    public long getCurrentTime() {
        return currentTime;
    }

    /**
     * Method returns the data object that holds more information about the bus information.
     * @return Returns an object containing more information about the bus arrival time.
     */
    public Data getData() {
        return data;
    }

    /**
     * Class created to hold more information about the bus arrival time.
     */
    public class Data {
        // This instance variable hold more information about the arrival time of the buses.
        private Entry entry;
        // Holds information description about the different buses.
        private References references;

        /**
         * Constructor used for assigning the instance variables.
         * @param entry Information about the arrival times.
         * @param references Information about the buses.
         */
        public Data(Entry entry, References references){
            this.entry = entry;
            this.references = references;
        }

        /**
         * Method returns the object that holds the arrival information.
         * @return Returns an entry class object.
         */
        public Entry getEntry() {
            return entry;
        }

        /**
         * Method returns the object that holds the bus description information.
         * @return Returns the object references.
         */
        public References getReferences() {
            return references;
        }

        /**
         * This class holds information about the different routes and their bus arrival times.
         */
        public class Entry {
            // List of all the different routes and they schedules.
            private List<Entry.StopRoute> stopRouteSchedules;

            /**
             * Constructor used for assigning the instance variable.
             * @param stopRouteSchedules The list of routes received from the link.
             */
            public Entry (List<Entry.StopRoute> stopRouteSchedules){
                this.stopRouteSchedules = stopRouteSchedules;
            }

            /**
             * Method that returns the list of all the routes.
             * @return Returns a list of the routes.
             */
            public List<Entry.StopRoute> getStopRouteSchedules() {
                return stopRouteSchedules;
            }

            /**
             * Class that stores information about the different stops and their bu arrival times.
             */
            public class StopRoute {
                // Id for the bus.
                private String routeID;
                private List<Entry.StopRoute.StopRouteDirect> stopRouteDirectionSchedules;

                public StopRoute(String routeID, List<Entry.StopRoute.StopRouteDirect> stopRouteDirectionSchedules){
                    this.routeID = routeID;
                    this.stopRouteDirectionSchedules = stopRouteDirectionSchedules;
                }

                public String getRouteID() {
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
                private String id;
                private long shortName;
                private long type;

                public Route(long angencyId, String description, String id, long shortName, long type){
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

                public String getId() {
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
