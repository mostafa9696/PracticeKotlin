package mostafa.example.com.practicekotlin.Model

import android.os.Parcel
import android.os.Parcelable


data class RepositoryOwner(
        val login: String,
        val id: Long,
        val avatar_url: String,
        val url: String,
        val type: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeLong(id)
        parcel.writeString(avatar_url)
        parcel.writeString(url)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepositoryOwner> {
        override fun createFromParcel(parcel: Parcel): RepositoryOwner {
            return RepositoryOwner(parcel)
        }

        override fun newArray(size: Int): Array<RepositoryOwner?> {
            return arrayOfNulls(size)
        }
    }
}