package com.web.demo.dto;
/**
 * @author An Nguyen
 */
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import com.web.demo.entity.BillDetail;
import com.web.demo.entity.Category;
import com.web.demo.entity.CommentGame;
import com.web.demo.entity.Discount;
import com.web.demo.entity.ImageData;
import com.web.demo.entity.SlideShow;
import com.web.demo.entity.Users;

public class GamesDtoAn {
	private Integer idGame;
	private Discount discount;
	private String nameGame;
	private String producterGame;
	private String publisherGame;
	private Date releaseYearGame;
	private String descriptionGame;
	private String linkVideo;
	private Long price;
	private Long priceFix;
	private Float rateGame;
	private Integer countSell;
	private String linkGame;
	private Integer countRate;
	private String processor;
	private String ram;
	private String freeStorage;
	private String vga;
	private Set<ImageDataDtoAn> imageDatas = new HashSet<ImageDataDtoAn>(0);
	private Set<CategoryDtoAn> categories = new HashSet<CategoryDtoAn>(0);
	private Set<Users> UsersActive = new HashSet<Users>(0);
	private Set<CommentGame> commentGames = new HashSet<CommentGame>(0);
	private Set<BillDetail> billDetails = new HashSet<BillDetail>(0);
	private Set<SlideShow> slideShows = new HashSet<SlideShow>(0);
	public Integer getIdGame() {
		return idGame;
	}
	public void setIdGame(Integer idGame) {
		this.idGame = idGame;
	}
	public Discount getDiscount() {
		return discount;
	}
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
	public String getNameGame() {
		return nameGame;
	}
	public void setNameGame(String nameGame) {
		this.nameGame = nameGame;
	}
	public String getProducterGame() {
		return producterGame;
	}
	public void setProducterGame(String producterGame) {
		this.producterGame = producterGame;
	}
	public String getPublisherGame() {
		return publisherGame;
	}
	public void setPublisherGame(String publisherGame) {
		this.publisherGame = publisherGame;
	}
	public Date getReleaseYearGame() {
		return releaseYearGame;
	}
	public void setReleaseYearGame(Date releaseYearGame) {
		this.releaseYearGame = releaseYearGame;
	}
	public String getDescriptionGame() {
		return descriptionGame;
	}
	public void setDescriptionGame(String descriptionGame) {
		this.descriptionGame = descriptionGame;
	}
	public String getLinkVideo() {
		return linkVideo;
	}
	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getPriceFix() {
		return priceFix;
	}
	public void setPriceFix(Long priceFix) {
		this.priceFix = priceFix;
	}
	public Float getRateGame() {
		return rateGame;
	}
	public void setRateGame(Float rateGame) {
		this.rateGame = rateGame;
	}
	public Integer getCountSell() {
		return countSell;
	}
	public void setCountSell(Integer countSell) {
		this.countSell = countSell;
	}
	public String getLinkGame() {
		return linkGame;
	}
	public void setLinkGame(String linkGame) {
		this.linkGame = linkGame;
	}
	public Integer getCountRate() {
		return countRate;
	}
	public void setCountRate(Integer countRate) {
		this.countRate = countRate;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getFreeStorage() {
		return freeStorage;
	}
	public void setFreeStorage(String freeStorage) {
		this.freeStorage = freeStorage;
	}
	public String getVga() {
		return vga;
	}
	public void setVga(String vga) {
		this.vga = vga;
	}
	public Set<ImageDataDtoAn> getImageDatas() {
		return imageDatas;
	}
	public void setImageDatas(Set<ImageDataDtoAn> imageDatas) {
		this.imageDatas = imageDatas;
	}
	
	public Set<CategoryDtoAn> getCategories() {
		return categories;
	}
	public void setCategories(Set<CategoryDtoAn> categories) {
		this.categories = categories;
	}
	public Set<Users> getUsersActive() {
		return UsersActive;
	}

	public void setUsersActive(Set<Users> usersActive) {
		UsersActive = usersActive;
	}
	public Set<CommentGame> getCommentGames() {
		return commentGames;
	}
	public void setCommentGames(Set<CommentGame> commentGames) {
		this.commentGames = commentGames;
	}
	public Set<BillDetail> getBillDetails() {
		return billDetails;
	}
	public void setBillDetails(Set<BillDetail> billDetails) {
		this.billDetails = billDetails;
	}
	public Set<SlideShow> getSlideShows() {
		return slideShows;
	}
	public void setSlideShows(Set<SlideShow> slideShows) {
		this.slideShows = slideShows;
	}
}
