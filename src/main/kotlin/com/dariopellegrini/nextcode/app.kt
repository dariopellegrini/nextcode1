package com.dariopellegrini.nextcode

import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt


/*
* Try here
* https://try.kotlinlang.org/#/UserProjects/isus118hm8mja479su9ruh627l/8kb714n8phqdkm9rhqjdrdc3mb
*
* Si parte dal presupposto che un numero se e' esagonale e' anche triangolare. Quindi l'obiettivo e' trovare
* quel numero che e' sia pentagonale che esagonale, tralasciando il triangolare.
*
* La condizione e' che Hn = Pn per diversi valori di n
* Quindi y(2y − 1) = x(3x − 1)/2
*
* Si parte dai valori dati nell'esercizio e si ciclano i valori di x (n pentagonale) finché non si trova un valore
* di y (n esagonale) che soddisfa la funzione e che sia intero.
*
* Si calcola quindi il numero esagonale che sarà lo stesso di quello pentagonale e di quello triangolare.
*
* Infine attraverso la formula triangolare inversa si calcola la n triangolare.
* */
fun main() {
    val data = PolygonData(
        triangleN = 285.0,
        pentagonN = 165.0,
        hexagonN = 143.0)

    do {
        data.pentagonN += 1
        data.hexagonN = hexFromPent(data.pentagonN)
    } while (!data.hexagonN.isInt)

    val pn = data.pentagon
    val en = data.hexagon
    data.triangleN = invertTriangle(en)
    val tn = data.triangle

    val allEquals = pn == en && en == tn

    println("""
        All equals: $allEquals
        
        Tn(${data.triangleN}) = Pn(${data.pentagonN}) = En(${data.hexagonN}) = $en
    """.trimIndent())
}

/*
* Funzione triangolare
* */
fun triangle(n: Double): Double {
    return (n * (n+1)) / 2
}

/*
* Funzione pentagonale
* */
fun pentagon(n: Double): Double {
    return  (n * (3*n - 1)) / 2
}

/*
* Funzione esagonale
* */
fun hexagon(n: Double): Double {
    return (n * (2*n - 1))
}

/*
* Funzione triangolare inversa
* */
fun invertTriangle(t: Double): Double {
    return (sqrt(8 * t + 1) - 1) / 2
}

/*
* Variabile che controlla se un double e' intero
* */
val Double.isInt get() = floor(this) == this

/*
* Funzione che calcola il valore n di Hn dal valore n di Pn usando la relazione
* x(3x − 1)/2 = y(2y − 1)
*
* Da cui il valore positivo
* y = 1/4 (sqrt(12 x^2 - 4 x + 1) + 1)
* */
fun hexFromPent(pent: Double): Double {
    return (sqrt(12 * (pent.pow(2)) - 4 * pent + 1) + 1)  / 4
}

/*
* Classe che contiene i diversi n cche calcolano i numeri triangolari, pentagonali ed esagonali
* */
data class PolygonData(var triangleN: Double, var pentagonN: Double, var hexagonN: Double)

/*
* Extension variable che calcolano i numeri triangolari, pentagonali ed esagonali di un'istanza PolygonData
* */
val PolygonData.triangle get() = triangle(this.triangleN)
val PolygonData.pentagon get() = pentagon(this.pentagonN)
val PolygonData.hexagon get() = hexagon(this.hexagonN)
