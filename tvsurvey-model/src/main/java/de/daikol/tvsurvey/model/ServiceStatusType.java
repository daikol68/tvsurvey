package de.daikol.tvsurvey.model;

import org.apache.commons.lang3.StringUtils;

public enum ServiceStatusType {
    /**
     * This type is used if an error occured.
     */
    ERROR(-1, "Unknown Error."),
    /**
     * This type is used if the call in general was successful but no entity could be found.
     */
    NOT_FOUND(-100, "Entity not found."),
    /**
     * This type is used if the call was successful.
     */
    SUCCESS(0, "Success");

    /**
     * The index of the type.
     */
    private final int index;

    /**
     * The name of the type.
     */
    private final String name;

    /**
     * The name lookup.
     */
    private final String nameLookup;

    /**
     * Constructor for a new StatusType.
     * 
     * @param index
     *            The index.
     * @param name
     *            The name.
     */
    private ServiceStatusType(int index, String name) {
        this.index = index;
        this.name = name;
        this.nameLookup = this.name.toLowerCase();
    }

    /**
     * This method is a getter for the member <code>index</code>.
     * 
     * @return Returns the member {@link ServiceStatusType#index index}.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * This method is a getter for the member <code>name</code>.
     * 
     * @return Returns the member {@link ServiceStatusType#name name}.
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method makes a lookup for the provided name in the enumeration. The lookup is case-insensitive and compares
     * the provided name with the attribute short name within the enumeration. If <code>null</code> is provided an
     * <code>IllegalArgumentException</code> will be thrown. If the name does not exist <code>null</code> will be
     * returned. As the attribute is unique the first result can be returned.
     * 
     * @param index
     *            The index to search for.
     * @return The ResultStatus with the index or <code>null</code> if non could be found.
     */
    public static ServiceStatusType getByIndex(int index) {
        for (ServiceStatusType type : ServiceStatusType.values()) {
            if (type.index == index) {
                return type;
            }
        }
        return null;
    }

    /**
     * This method makes a lookup for the provided name in the enumeration. The lookup is case-insensitive and compares
     * the provided name with the attribute name within the enumeration. If <code>null</code> is provided an
     * <code>IllegalArgumentException</code> will be thrown. If the name does not exist <code>null</code> will be
     * returned. As the attribute is unique the first result can be returned.
     * 
     * @param name
     *            The name to search for.
     * @return The ResultStatus with the name or <code>null</code> if non could be found.
     */
    public static ServiceStatusType getByName(String name) {

        // early out
        if (StringUtils.isBlank(name)) {
            return null;
        }

        // lookup preparation
        String lookup = name.trim().toLowerCase();

        for (ServiceStatusType type : ServiceStatusType.values()) {
            if (type.nameLookup.equals(lookup)) {
                return type;
            }
        }
        return null;
    }
}
