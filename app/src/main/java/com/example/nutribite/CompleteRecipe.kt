import android.os.Parcel
import android.os.Parcelable

data class NutritionInfo(
    val calories: Int,
    val protein: Double,
    val fat: Double,
    val carbs: Double
)

data class Recipe(
    val category: String,
    val dishName: String,
    val ingredients: List<String>,
    val recipe: String,
    val nutritionalInfo: NutritionInfo,
    val imageResource: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        NutritionInfo(parcel.readInt(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble()),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeString(dishName)
        parcel.writeStringList(ingredients)
        parcel.writeString(recipe)
        parcel.writeInt(nutritionalInfo.calories)
        parcel.writeDouble(nutritionalInfo.protein)
        parcel.writeDouble(nutritionalInfo.fat)
        parcel.writeDouble(nutritionalInfo.carbs)
        parcel.writeInt(imageResource)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}
