fun main() {
    findMiddleIndex(listOf(1, 3, 5, 7, 9))
    findMiddleIndex(listOf(3, 6, 8, 1, 5, 10, 1, 7))
    findMiddleIndex(listOf(3, 5, 6))

    checkPalindrome("aka")
    checkPalindrome("Level")
    checkPalindrome("Hello")
}

fun findMiddleIndex(data: List<Int>) {
    for (index in data.indices) {
        if (data.isMiddle(index)) {
            print("middle index is $index\n")
            return
        }
    }
    print("index not found\n")
}

fun List<Int>.isMiddle(index: Int): Boolean {
    if (size < 3) return false
    if (index == 0) return sumOfRange(1, this.size) == 0
    if (index == lastIndex) return sumOfRange(0, this.size - 1) == 0
    return sumOfRange(0, index) == sumOfRange(index + 1, this.size)
}

fun List<Int>.sumOfRange(from: Int, to: Int): Int {
    var sum: Int = 0
    for (index in from until to) {
        sum += this[index]
    }
    return sum
}

fun checkPalindrome(input: String) {
    print(if (input.isPalindrome()) "$input is a palindrome\n" else "$input isn’t a palindrome\n")
}

fun String.isPalindrome(): Boolean {
    if (isEmpty()) return false
    val builder: StringBuilder = StringBuilder()
    for (index in this.length - 1 downTo 0) {
        builder.append(this[index])
    }
    return builder.toString().uppercase() == uppercase()
}