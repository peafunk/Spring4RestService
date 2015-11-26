package com.peafunk.spring4rest.exception;

@SuppressWarnings("serial")
public class Spring4RestServiceFolderNotFoundException extends Exception {

	protected String name;
	protected int id;

	protected String message;
	protected String description;
	protected Exception exception;
	
    /**
     * Creates the exception, setting the id or name to indicate how it was being search for.
     * 
     * @param id the identifier of the bucket.
     * @param name the name of the bucket.
     */
    public Spring4RestServiceFolderNotFoundException(int id, String name) {
        super("Unable to find bucket with " + (id == 0 ? "name '" + name + "'" : "id '" + "'"));
        this.id = id;
        this.name = name;
    }
    
	public Spring4RestServiceFolderNotFoundException(String message, String description, Exception e) {
		this.message = message;
		this.description = description;
		this.exception = e;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
