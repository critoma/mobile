// https://pl.kotl.in/pzn9O-r0l

package eu.ism.kt


class MyISMClass {
    private var name: String = "This is a String field within class MyISMClass"
    
    // https://stackoverflow.com/questions/55356837/what-is-the-difference-between-init-block-and-constructor-in-kotlin
    init {
        println("\n MyISMClass init block -> " + this)
    }
    
    fun printMe() {
        print("\n This object = " + this + " has field name = " + this.name)
    }
}

class MyTime : Cloneable {
    var h: Int
    var m: Int
    var s: Int
    
    constructor() {
        this.h = 0; this.m = 0; this.s = 0;
    }
    constructor(hours: Int, minutes: Int, seconds: Int) {
        this.h = hours
        this.m = minutes
        this.s = seconds
    }
    
    fun setMinutes(minutes: Int) {
        this.m = minutes
    }
    
    fun serialize(): String {
        return "H:m:s = {%d}:{%d}:{%d}".format(this.h, this.m, this.s)
    }
    
    // https://developermemos.com/posts/clone-object-kotlin
    // https://discuss.kotlinlang.org/t/how-to-use-cloneable/2364
    public override fun clone(): Any {
        var t = super.clone() as MyTime
        t.h = this.h
        t.m = this.m
        t.s = this.s
        return t
    } 
}

fun main(args: Array<String>) {
    println("Second Kotlin program argument is = " + args[1])
    val obj01 = MyISMClass()
    obj01.printMe()
    val obj02 = MyISMClass()
    obj02.printMe()
    println()
    
    var t1 = MyTime(10, 8, 43)
    println("t1 -> " + t1.serialize())
    var t2 = MyTime(10, 11, 25)
    println("t2 -> " + t2.serialize())
    var t3 = t2
    t3.setMinutes(15)
    println("t2 -> " + t2.serialize() + ", t3 -> " + t3.serialize())
    
    var t4 = t2.clone() as MyTime
    t4.setMinutes(43)
    println("t2-> "+t2.serialize()+", t4-> "+t4.serialize())
}

/*
fun mulBy2(x:Int): Int {
    var r: Int
    r = x * 2
    return r
}

fun myMul(x:Int, y: Int): Int {
    return x * y
}
*/

//fun main(args: Array<String>){
/*
    var w:Int = 15
    println("w * 2 = " + mulBy2(w))
    var v:Int = 12
    println("w * v = " + myMul(w, v))
    
    val items = listOf(9, 3, 11, -2)
    for(i in 0 .. items.lastIndex) {
        println("items[" + i + "]=" + items[i])
    }
    for(objIdx in items) {
        println("objIdx = " + objIdx.toString())
    }
    
    var z: Int = 0
    while (z <= 5) {
        print(" z = " + z + ",")
        z++
    }
    
    val x: Int = 9
    when(x) {
        1 -> println("x == 1")
        2 -> println("x == 2")
        else -> {
            println("x != 1 and x != 2")
        }
    }
    
    val a: Int = 5
    val b: Int = 2
    var max: Int
    
    if (a > b) {
        max = a
    } else {
        max = b
    }
    println("max = " + max)
  */  
  // JS
  // val json = js("{}")               // 1
  // json.name = "Jane"                // 2
  // json.hobby = "movies"
  
  // println(JSON.stringify(json))     // 3
  
  //println("second command line param is = " + args[1])
  /*
  var x: Int = 1245
  x++
  println("x = " + x)
  
  var y:Double = 100.56
  y--;
  println("y = " + y)
  
  var eStr : String = "I am a String!!! \n"
  eStr += "..."
  println("Hello - " + eStr)
  */
// }
