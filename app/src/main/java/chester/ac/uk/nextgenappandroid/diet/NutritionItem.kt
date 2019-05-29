package chester.ac.uk.nextgenappandroid.diet

enum class Nutrient {
    FAT,
    CARBOHYDRATE,
    PROTEIN
}

class NutritionItem(var nutrient: Nutrient, var amount: Double)