import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readLines(name: String, test: Boolean = false) = Path("src/$name/$name${if (test) "_test" else ""}.txt").readText().trim().lines()

/**
 * Reads text of the given input file
 */
fun readText(name: String, test: Boolean = false) = Path("src/$name/$name${if (test) "_test" else ""}.txt").readText().trim()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
