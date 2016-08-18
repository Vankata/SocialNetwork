package user;

import wall.Post;

public interface IUser {
	//mojem da haresvame samo na drugite
	public abstract void likePost(User friend, Post post);
	//mojem da kometirame na drugite i na nas
	public abstract void commentPost(User user, Post post, String comment);
	//mojem da triem samo nashi postove
	//shte proverqvame dali postyt e instanceof Photo
	public abstract void deletePost(Post post);
	//mojem da reportvame samo postove na priqteli
	public abstract void reportPost(User friend, Post post);
	//dobavqme posta na nashata stena
	public abstract void addPost(Post post);
	//---------------------------------------
	//proverqvame dali syshtestvuva v userStatus i dali veche go imame 
	public abstract void addFriend(User user);
	//proverqvame dali go imame v priqteli
	public abstract void removeFirend(User user);
	//pokazva infoto na friend
	public abstract void reviewFriendInfo(User friend);
	//pokazva stenata na friend
	public abstract void reviewFriendWall(User firend);
	//---------------------------------------
	//trie profila ot userStatus
	public abstract void deleteProfile(String password, String email);
	//izlizame ot accounta, zarejda ni guest stranicata
	public abstract void logout();
	//---------------------------------------
	//prashtame syobshtenie
	//public abstract void sendMessage(User friend, String message);
	//preglejdame chata s nqkoi
	//public abstract void reviewChat(User friend);
}
