package mostafa.example.com.practicekotlin.Model

import android.os.Parcel
import android.os.Parcelable


data class Reporistory (
        val id : Long,
        val name: String,
        val full_name: String,
        val owner: RepositoryOwner,
        val html_url: String,
        val description: String?,
        val url: String,
        val created_at: String,
        val updated_at: String,
        val pushed_at: String,
        val homepage: String?,
        val stargazers_count: Long,
        val watchers_count: Long,
        val watchers: String,
        val score: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(RepositoryOwner::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readDouble()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(full_name)
        parcel.writeParcelable(owner, flags)
        parcel.writeString(html_url)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
        parcel.writeString(pushed_at)
        parcel.writeString(homepage)
        parcel.writeLong(stargazers_count)
        parcel.writeLong(watchers_count)
        parcel.writeString(watchers)
        parcel.writeDouble(score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Reporistory> {
        override fun createFromParcel(parcel: Parcel): Reporistory {
            return Reporistory(parcel)
        }

        override fun newArray(size: Int): Array<Reporistory?> {
            return arrayOfNulls(size)
        }
    }
}
