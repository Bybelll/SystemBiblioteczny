package SystemB;

class Data {
	  private String ID;
	
	  private String title;
	
	  private String year;
	
	  private String ISBN;
	
	  private String wydawca;
	
	  private String kategoria;
	  
	  public Data() {
	  }
	
	  public Data(String ID, String title, String year, String ISBN, String wydawca, String kategoria) {
	    this.ID=ID;
	    this.title=title;
	    this.year=year;
	    this.ISBN=ISBN;
	    this.wydawca=wydawca;
	    this.kategoria=kategoria;
	  }
	
	  public String getID() {
	    return ID;
	  }
	
	  public String getTitle() {
	    return title;
	  }
	
	  public String getYear() {
		    return year;
		  }
	  
	  public String getISBN() {
		    return ISBN;
		  }
	  
	  public String getwydawca() {
		    return wydawca;
		  }
	
	  public String getkategoria() {
		    return kategoria;
		  }
	  
	}