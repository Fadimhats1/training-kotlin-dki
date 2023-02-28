package com.example.trainingapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class AnimesResponse(@field:SerializedName("data") val data: List<Anime?>? = null):Parcelable {
	constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Anime)) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeTypedList(data)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<AnimesResponse> {
		override fun createFromParcel(parcel: Parcel): AnimesResponse {
			return AnimesResponse(parcel)
		}

		override fun newArray(size: Int): Array<AnimesResponse?> {
			return arrayOfNulls(size)
		}
	}
}


data class Anime(

	@field:SerializedName("title_japanese")
	val titleJapanese: String? = null,

	@field:SerializedName("favorites")
	val favorites: Int? = null,

	@field:SerializedName("broadcast")
	val broadcast: Broadcast? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("scored_by")
	val scoredBy: Int? = null,

	@field:SerializedName("title_synonyms")
	val titleSynonyms: List<String?>? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("trailer")
	val trailer: Trailer? = null,

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("score")
	val score: Double? = null,

	@field:SerializedName("themes")
	val themes: List<ThemesItem?>? = null,

	@field:SerializedName("approved")
	val approved: Boolean? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null,

	@field:SerializedName("popularity")
	val popularity: Int? = null,

	@field:SerializedName("members")
	val members: Int? = null,

	@field:SerializedName("title_english")
	val titleEnglish: String? = null,

	@field:SerializedName("rank")
	val rank: Int? = null,

	@field:SerializedName("season")
	val season: String? = null,

	@field:SerializedName("airing")
	val airing: Boolean? = null,

	@field:SerializedName("episodes")
	val episodes: Int? = null,

	@field:SerializedName("aired")
	val aired: Aired? = null,

	@field:SerializedName("images")
	val images: Images? = null,

	@field:SerializedName("studios")
	val studios: List<StudiosItem?>? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("titles")
	val titles: List<TitlesItem?>? = null,

	@field:SerializedName("synopsis")
	val synopsis: String? = null,

	@field:SerializedName("licensors")
	val licensors: List<LicensorsItem?>? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("producers")
	val producers: List<ProducersItem?>? = null,

	@field:SerializedName("background")
	val background: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("demographics")
	val demographics: List<DemographicsItem?>? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readTypedObject(Broadcast.CREATOR),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.createStringArrayList(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readTypedObject(Trailer.CREATOR),
		parcel.readString(),
		parcel.readValue(Double::class.java.classLoader) as? Double,
		parcel.createTypedArrayList(ThemesItem),
		parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
		parcel.createTypedArrayList(GenresItem),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readTypedObject(Aired.CREATOR),
		parcel.readTypedObject(Images.CREATOR),
		parcel.createTypedArrayList(StudiosItem),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.createTypedArrayList(TitlesItem),
		parcel.readString(),
		parcel.createTypedArrayList(LicensorsItem),
		parcel.readString(),
		parcel.createTypedArrayList(ProducersItem),
		parcel.readString(),
		parcel.readString(),
		parcel.createTypedArrayList(DemographicsItem)
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(titleJapanese)
		parcel.writeValue(favorites)
		parcel.writeTypedObject(broadcast, flags)
		parcel.writeValue(year)
		parcel.writeString(rating)
		parcel.writeValue(scoredBy)
		parcel.writeStringList(titleSynonyms)
		parcel.writeString(source)
		parcel.writeString(title)
		parcel.writeString(type)
		parcel.writeTypedObject(trailer, flags)
		parcel.writeString(duration)
		parcel.writeValue(score)
		parcel.writeTypedList(themes)
		parcel.writeValue(approved)
		parcel.writeTypedList(genres)
		parcel.writeValue(popularity)
		parcel.writeValue(members)
		parcel.writeString(titleEnglish)
		parcel.writeValue(rank)
		parcel.writeString(season)
		parcel.writeValue(airing)
		parcel.writeValue(episodes)
		parcel.writeTypedObject(aired, flags)
		parcel.writeTypedObject(images, flags)
		parcel.writeTypedList(studios)
		parcel.writeValue(malId)
		parcel.writeTypedList(titles)
		parcel.writeString(synopsis)
		parcel.writeTypedList(licensors)
		parcel.writeString(url)
		parcel.writeTypedList(producers)
		parcel.writeString(background)
		parcel.writeString(status)
		parcel.writeTypedList(demographics)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Anime> {
		override fun createFromParcel(parcel: Parcel): Anime {
			return Anime(parcel)
		}

		override fun newArray(size: Int): Array<Anime?> {
			return arrayOfNulls(size)
		}
	}
}

data class Broadcast(

	@field:SerializedName("string")
	val string: String? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("day")
	val day: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(string)
		parcel.writeString(timezone)
		parcel.writeString(time)
		parcel.writeString(day)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Broadcast> {
		override fun createFromParcel(parcel: Parcel): Broadcast {
			return Broadcast(parcel)
		}

		override fun newArray(size: Int): Array<Broadcast?> {
			return arrayOfNulls(size)
		}
	}
}

data class Prop(

	@field:SerializedName("from")
	val from: From? = null,

	@field:SerializedName("to")
	val to: To? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readTypedObject(From.CREATOR),
		parcel.readTypedObject(To.CREATOR)
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeTypedObject(from, flags)
		parcel.writeTypedObject(to, flags)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Prop> {
		override fun createFromParcel(parcel: Parcel): Prop {
			return Prop(parcel)
		}

		override fun newArray(size: Int): Array<Prop?> {
			return arrayOfNulls(size)
		}
	}
}

data class Jpg(

	@field:SerializedName("large_image_url")
	val largeImageUrl: String? = null,

	@field:SerializedName("small_image_url")
	val smallImageUrl: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(largeImageUrl)
		parcel.writeString(smallImageUrl)
		parcel.writeString(imageUrl)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Jpg> {
		override fun createFromParcel(parcel: Parcel): Jpg {
			return Jpg(parcel)
		}

		override fun newArray(size: Int): Array<Jpg?> {
			return arrayOfNulls(size)
		}
	}
}

data class Trailer(

	@field:SerializedName("images")
	val images: Images? = null,

	@field:SerializedName("embed_url")
	val embedUrl: String? = null,

	@field:SerializedName("youtube_id")
	val youtubeId: String? = null,

	@field:SerializedName("url")
	val url: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readTypedObject(Images.CREATOR),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeTypedObject(images, flags)
		parcel.writeString(embedUrl)
		parcel.writeString(youtubeId)
		parcel.writeString(url)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Trailer> {
		override fun createFromParcel(parcel: Parcel): Trailer {
			return Trailer(parcel)
		}

		override fun newArray(size: Int): Array<Trailer?> {
			return arrayOfNulls(size)
		}
	}
}

data class TitlesItem(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("title")
	val title: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(type)
		parcel.writeString(title)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<TitlesItem> {
		override fun createFromParcel(parcel: Parcel): TitlesItem {
			return TitlesItem(parcel)
		}

		override fun newArray(size: Int): Array<TitlesItem?> {
			return arrayOfNulls(size)
		}
	}
}

data class DemographicsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeValue(malId)
		parcel.writeString(type)
		parcel.writeString(url)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<DemographicsItem> {
		override fun createFromParcel(parcel: Parcel): DemographicsItem {
			return DemographicsItem(parcel)
		}

		override fun newArray(size: Int): Array<DemographicsItem?> {
			return arrayOfNulls(size)
		}
	}
}

data class Images(

	@field:SerializedName("jpg")
	val jpg: Jpg? = null,

	@field:SerializedName("webp")
	val webp: Webp? = null,

	@field:SerializedName("large_image_url")
	val largeImageUrl: String? = null,

	@field:SerializedName("small_image_url")
	val smallImageUrl: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("medium_image_url")
	val mediumImageUrl: String? = null,

	@field:SerializedName("maximum_image_url")
	val maximumImageUrl: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readTypedObject(Jpg.CREATOR),
		parcel.readTypedObject(Webp.CREATOR),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeTypedObject(jpg, flags)
		parcel.writeTypedObject(webp, flags)
		parcel.writeString(largeImageUrl)
		parcel.writeString(smallImageUrl)
		parcel.writeString(imageUrl)
		parcel.writeString(mediumImageUrl)
		parcel.writeString(maximumImageUrl)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Images> {
		override fun createFromParcel(parcel: Parcel): Images {
			return Images(parcel)
		}

		override fun newArray(size: Int): Array<Images?> {
			return arrayOfNulls(size)
		}
	}
}

data class GenresItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeValue(malId)
		parcel.writeString(type)
		parcel.writeString(url)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<GenresItem> {
		override fun createFromParcel(parcel: Parcel): GenresItem {
			return GenresItem(parcel)
		}

		override fun newArray(size: Int): Array<GenresItem?> {
			return arrayOfNulls(size)
		}
	}
}

data class LicensorsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeValue(malId)
		parcel.writeString(type)
		parcel.writeString(url)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<LicensorsItem> {
		override fun createFromParcel(parcel: Parcel): LicensorsItem {
			return LicensorsItem(parcel)
		}

		override fun newArray(size: Int): Array<LicensorsItem?> {
			return arrayOfNulls(size)
		}
	}
}

data class ThemesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeValue(malId)
		parcel.writeString(type)
		parcel.writeString(url)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ThemesItem> {
		override fun createFromParcel(parcel: Parcel): ThemesItem {
			return ThemesItem(parcel)
		}

		override fun newArray(size: Int): Array<ThemesItem?> {
			return arrayOfNulls(size)
		}
	}
}

data class StudiosItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeValue(malId)
		parcel.writeString(type)
		parcel.writeString(url)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<StudiosItem> {
		override fun createFromParcel(parcel: Parcel): StudiosItem {
			return StudiosItem(parcel)
		}

		override fun newArray(size: Int): Array<StudiosItem?> {
			return arrayOfNulls(size)
		}
	}
}

data class Webp(

	@field:SerializedName("large_image_url")
	val largeImageUrl: String? = null,

	@field:SerializedName("small_image_url")
	val smallImageUrl: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(largeImageUrl)
		parcel.writeString(smallImageUrl)
		parcel.writeString(imageUrl)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Webp> {
		override fun createFromParcel(parcel: Parcel): Webp {
			return Webp(parcel)
		}

		override fun newArray(size: Int): Array<Webp?> {
			return arrayOfNulls(size)
		}
	}
}

data class ProducersItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeValue(malId)
		parcel.writeString(type)
		parcel.writeString(url)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ProducersItem> {
		override fun createFromParcel(parcel: Parcel): ProducersItem {
			return ProducersItem(parcel)
		}

		override fun newArray(size: Int): Array<ProducersItem?> {
			return arrayOfNulls(size)
		}
	}
}

data class Aired(

	@field:SerializedName("string")
	val string: String? = null,

	@field:SerializedName("prop")
	val prop: Prop? = null,

	@field:SerializedName("from")
	val from: String? = null,

	@field:SerializedName("to")
	val to: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readTypedObject(Prop.CREATOR),
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(string)
		parcel.writeTypedObject(prop, flags)
		parcel.writeString(from)
		parcel.writeString(to)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Aired> {
		override fun createFromParcel(parcel: Parcel): Aired {
			return Aired(parcel)
		}

		override fun newArray(size: Int): Array<Aired?> {
			return arrayOfNulls(size)
		}
	}
}

data class From(

	@field:SerializedName("month")
	val month: Int? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("day")
	val day: Int? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(month)
		parcel.writeValue(year)
		parcel.writeValue(day)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<From> {
		override fun createFromParcel(parcel: Parcel): From {
			return From(parcel)
		}

		override fun newArray(size: Int): Array<From?> {
			return arrayOfNulls(size)
		}
	}
}

data class To(

	@field:SerializedName("month")
	val month: Int? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("day")
	val day: Int? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(month)
		parcel.writeValue(year)
		parcel.writeValue(day)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<To> {
		override fun createFromParcel(parcel: Parcel): To {
			return To(parcel)
		}

		override fun newArray(size: Int): Array<To?> {
			return arrayOfNulls(size)
		}
	}
}
