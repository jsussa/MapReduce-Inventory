/*
 * Jesus Arredondo
 *  2/27/2018
 *  Advanced Database Class
 *  Final Project: Inventory MapReduce
*/

public class Product {

	public String pub_id;
	public Long inv_total;
	public String[] comments;
	
	public String getPub_id() {
		return pub_id;
	}
	
	public void setPub_id(String pub_id) {
		this.pub_id = pub_id;
	}
	
	public Long getInv_total() {
		return inv_total;
	}
	
	public void setInv_total(Long inv_total) {
		this.inv_total = inv_total;
	}
	
	public String[] getComments() {
		return comments;
	}
	
	public void setComments(String[] comments) {
		this.comments = comments;
	}
}
