package com.sound.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.github.jmkgreen.morphia.annotations.Embedded;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Id;
import com.github.jmkgreen.morphia.annotations.Reference;
import com.github.jmkgreen.morphia.annotations.Serialized;
import com.sound.model.enums.SoundState;
import com.sound.model.enums.SoundType;

@Entity(noClassnameStored = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sound extends BaseModel {

	@Id
	private ObjectId id;

	@Embedded
	private SoundProfile profile;
	
	@Embedded
	private SoundSocial soundSocial;

	@Reference
	private SoundData soundData;

	@Reference(lazy=true)
	private List<Sound> innerSounds;

	@Reference(lazy = true)
	private List<Sound> sets;
	
	@Reference(lazy = true)
	private List<Tag> tags;

	@JsonIgnore
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public SoundProfile getProfile() {
		return profile;
	}

	public void setProfile(SoundProfile profile) {
		this.profile = profile;
	}

	public SoundData getSoundData() {
		return soundData;
	}

	public void setSoundData(SoundData soundData) {
		this.soundData = soundData;
	}

	public SoundSocial getSoundSocial() {
		return soundSocial;
	}

	public void setSoundSocial(SoundSocial soundSocial) {
		this.soundSocial = soundSocial;
	}

	public List<Sound> getInnerSounds() {
		return innerSounds;
	}

	public void setInnerSounds(List<Sound> innerSounds) {
		this.innerSounds = innerSounds;
	}

	public List<Sound> getSets() {
		return sets;
	}

	public void setSets(List<Sound> sets) {
		this.sets = sets;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void addTags(List<Tag> tags) {
		if (this.tags == null) {
			this.tags = new ArrayList<Tag>();
		}
		this.tags.addAll(tags);
	}

	public static class SoundProfile {
		@Reference(lazy = true)
		private User owner;

		@Embedded
		private SoundPoster poster;

		private String name;

		private String description;

		/**
		 * published, private, deleted
		 */
		private int status;

		/**
		 * sound, set
		 */
		private int type;

		/**
		 * times played
		 */
		private int played;

		private Date createdTime;

		private Date modifiedTime;

		private boolean downloadable;

		public User getOwner() {
			return owner;
		}

		public void setOwner(User owner) {
			this.owner = owner;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getStatus() {
			return SoundState.getStateName(status);
		}

		public void setStatus(String status) {
			this.status = SoundState.getStateId(status);
		}

		public String getType() {
			return SoundType.getTypeName(this.type);
		}

		public void setType(String type) {
			this.type = SoundType.getTypeId(type);
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getPlayed() {
			return played;
		}

		public void setPlayed(int played) {
			this.played = played;
		}

		public Date getCreatedTime() {
			return createdTime;
		}

		public void setCreatedTime(Date createdTime) {
			this.createdTime = createdTime;
		}

		public Date getModifiedTime() {
			return modifiedTime;
		}

		public void setModifiedTime(Date modifiedTime) {
			this.modifiedTime = modifiedTime;
		}

		public SoundPoster getPoster() {
			return poster;
		}

		public void setPoster(SoundPoster poster) {
			this.poster = poster;
		}

		public boolean isDownloadable() {
			return downloadable;
		}

		public void setDownloadable(boolean downloadable) {
			this.downloadable = downloadable;
		}

		public static class SoundPoster {
			// Id of stored poster
			private String posterId;

			private String extension;

			public String getPosterId() {
				return posterId;
			}

			public void setPosterId(String posterId) {
				this.posterId = posterId;
			}

			public String getExtension() {
				return extension;
			}

			public void setExtension(String extension) {
				this.extension = extension;
			}
		}
	}

	@Entity(noClassnameStored = true)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SoundData {
		@Id
		private ObjectId id;

		// meide route in resource server.
		private String objectId;

		private int duration;

		@Serialized
		private float[][] wave;

		public String getObjectId() {
			return objectId;
		}

		public void setObjectId(String objectId) {
			this.objectId = objectId;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public float[][] getWave() {
			return wave;
		}

		public void setWave(float[][] wave) {
			this.wave = wave;
		}

		@JsonIgnore
		public ObjectId getId() {
			return id;
		}

		public void setId(ObjectId id) {
			this.id = id;
		}

	}
	
	public static class SoundSocial
	{
		private Integer likesCount;
		
		private Integer reportsCount;
		
		private Integer commentsCount;

		public Integer getLikesCount() {
			return likesCount;
		}

		public void setLikesCount(Integer likesCount) {
			this.likesCount = likesCount;
		}

		public Integer getReportsCount() {
			return reportsCount;
		}

		public void setReportsCount(Integer reportsCount) {
			this.reportsCount = reportsCount;
		}

		public Integer getCommentsCount() {
			return commentsCount;
		}

		public void setCommentsCount(Integer commentsCount) {
			this.commentsCount = commentsCount;
		}
		
	}
	
}
