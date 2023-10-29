package eu.ism.kt

// https://shell.cloud.google.com/?show=ide%2Cterminal&authuser=3&fromcloudshell=true
/*
cd ~/kotlin/software
# https://github.com/JetBrains/kotlin/releases
# wget https://github.com/JetBrains/kotlin/releases/download/v1.7.20/kotlin-compiler-1.7.20.zip
wget https://github.com/JetBrains/kotlin/releases/download/v1.9.10/kotlin-compiler-1.9.10.zip
# unzip kotlin-compiler-1.7.20.zip
unzip kotlin-compiler-1.9.20.zip
cd ../ktsrc
export KOTLIN_HOME=~/kotlin/software/kotlinc
export PATH=.:$KOTLIN_HOME/bin:$PATH

mkdir -p eu/ism/kt
export KOTLIN_HOME=~/kotlin/software/kotlinc
export PATH=.:$KOTLIN_HOME/bin:$PATH

# kotlinc test01.kt

secitc@cloudshell:~/kotlin/ktsrc$ java -cp .:../software/kotlinc/lib/* eu.ism.kt.Test01Kt
Hello, World...!
secitc@cloudshell:~/kotlin/ktsrc$ kotlin eu.ism.kt.Test01Kt
*/
// https://pl.kotl.in/H2y34z35l

// interface:

interface ExampleInterface  {
    var myVar: Int             // abstract property
   	fun absMethod(): String    // abstract method
   
   	fun hello() {
    	println("Hello there, Welcome to Default Method in the interface!")
    }
}

class InterfaceImpl : ExampleInterface {
   	override var myVar: Int = 25
   	override fun absMethod() = "Happy Learning "
    // hello() meth is not override here
}

fun main() {
   	val obj = InterfaceImpl()
   	println("My Variable Value is = ${obj.myVar}")
   	print("Calling hello(): ")
   	obj.hello()
   
   	print("Message from the Website-- ")
   	println(obj.absMethod())
}


/*
// complex inheritance
open class Vehicle {
    private var weight: Float
    var weightProperty: Float
    	get() = weight
    	set(value) { weight = value }
    
    constructor(weight: Float = 0.0f) {
        this.weight = weight
    }
    
    open fun printMe():String {
        val r:String = "Vehicle class - weight = " + this.weight
        println(r)
        return r
    }
    
}

class Auto: Vehicle {
    
    private var doorsNo: Int
    var doorsNoProperty: Int
    	get() = doorsNo
    	set(value) { doorsNo = value }
    
    constructor(weight: Float = 0.0f, doorsNo: Int = 0):super(weight) {
        this.doorsNo = doorsNo
    }
    
    override fun printMe():String {
        val r:String = "Auto class - weight = " + this.weightProperty + ", doorsNo = " + this.doorsNo
        println(r)
        return r
    }
}

class Plane: Vehicle {
    private var capacity: Float
    var capacityProperty: Float
    	get() = capacity
    	set(value) { capacity = value }
    
    private var enginesNo: Int
    
    constructor(weight: Float = 0.0f, capacity: Float = 0.0f, enginesNo: Int = 0) : super(weight) {
        this.capacity = capacity
        this.enginesNo = enginesNo
    }
    
    override fun printMe():String {
        val r:String = "Plane class - w = " + this.weightProperty + ", capacity = " + capacity + 
               ", enginesNo = " + enginesNo
        println(r)
        return r
    }
}

fun main() {
    var a = Auto(1200.0f, 3)
    var p = Plane(11500.0f, 300.0f, 4)
    
    a.printMe()
    val ss:String = p.printMe()
    println("ss = " + ss)
    
    var p2 = p
    p2.capacityProperty = 2018.0f
    println("p ---")
    p.printMe()
    println("p2 ---")
    p2.printMe()
    // ClassCastException v:Vehicle ; a -> v -> p
    var v:Vehicle
    v = a // a -> v
    println("v ==> ")
    v.printMe()
    p = v as Plane // v -> p
    p.printMe()
    
    println("When you are learning a language: " +
            "\n 1. Translate the business logic reqs into logic dataflow" +
            "\n 2. Generate simple sequential code for implementing the logic dataflow" +
            "\n 3. Translate the code into proper paradigm: OOP, Functional programming, etc." +
            "\n 4. Understand the synthax and the language internals with the debugger and memory layout")
}
*/

/*
// inheritance
open class Vehicle {
    open fun printMe() {
        println("Vehicle class")
    }
}

class Auto: Vehicle() {
    override fun printMe() {
        println("Auto class")
    }
}

class Plane: Vehicle() {
    override fun printMe() {
        println("Plane class")
    }
}

fun main() {
    var a = Auto()
    var p = Plane()
    
    a.printMe()
    p.printMe()
}
*/

// ####################################################
    
/*
class MyTime : Cloneable {
    var h: Int
     get() = field
     set(value) {
        field = value
     }
    
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

//class MyISMClass {
class MyISMClass (nameStrParam: String? = null) {
    //private var name: String = "This is a String field within class MyISMClass"
    private var name: String? = nameStrParam
    
    // https://stackoverflow.com/questions/55356837/what-is-the-difference-between-init-block-and-constructor-in-kotlin
    init {
        println("\n MyISMClass init block -> " + this)
    }
    
    fun printMe() {
        print("\n This object = " + this + " has field name = " + this.name)
    }
}

class Human(val firstName: String, var age: Int) {
    var message: String = "Hello! "
    
    constructor (name: String, age: Int, msg: String) : this(name, age) {
        this.message = msg
    }
}

fun main(args: Array<String>) {
    println("Second Kotlin program argument is = " + args[1])
    
    val obj01 = MyISMClass()
    obj01.printMe()
    val obj02 = MyISMClass()
    obj02.printMe()
    var obj03 = MyISMClass("Wow")
    obj03.printMe()
    
    val objHuman01 = Human("Not Robot 01", 23)
    println("\n" + "${objHuman01.message}, ${objHuman01.firstName} , your age is: ${objHuman01.age}")
    var objHuman02 = Human("Not Robot 02", 25, "Bonjour! ")
    println("\n" + "${objHuman02.message}, ${objHuman02.firstName} , your age is: ${objHuman02.age}")
    
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
    
    var t5: MyTime?
    if(12 == t2.h) { // if(10 == t2.h) {
        t5 = null
        t2.h = 11 
        println("t2-> "+t2.serialize())
    } else {
        t5 = MyTime()
    }
    //if(t5 != null)
    //	println(t5.serialize())
    println(t5?.serialize()) // t5?.let{}
}
*/

// ##################################################


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
//}
