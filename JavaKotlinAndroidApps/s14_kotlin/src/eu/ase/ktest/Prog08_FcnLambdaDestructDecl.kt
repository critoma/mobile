
package eu.dice.ism.ktest
//data class Student(val a: String, val b: String) {
class Student(val a: String, val b: String) {
    var name: String = a
    var id: String = b
    
    fun printStudentWLamda(action: (String) -> Unit ) {
    	action(this.id + "," + this.name)
	}
}
fun MyFun(x: String):String {
    var c:String = "Hey! "
    return (c + x)
}
fun MyFunFP(a: String, action: (String) -> Unit ) {
    print("\n Hello Functional Programming - ")
    action(a)
}

fun main(args: Array<String>) {
    // lambda expression:
    val myLambda: (s:String) -> Unit = {
        s:String -> println(s)
		//var sr:String = s; sr += "..."; println(sr)
    }
    // 1. std fun call
    val fresult = MyFun("Hello from std function")
    println(fresult)
   
    
    // 2. Lambda call simple:
    myLambda("Hello Lambda from Kotlin")
    
    // 3. Lambda call Functional Programming:
    MyFunFP("Playing with Kotlin ", myLambda)
    
    // 4. OOP + FP
    val s = Student("John", "5010721cnp")
    s.printStudentWLamda(myLambda)
}

/*
package eu.ase.ktest

// destructuring declarations:
data class Student(val a :String, val b: String ) {
   	var name: String = a
   	var subject: String = b
}

fun main(args: Array<String>) {
    // standard function call:
    val fr: String = MyFunction("tutorials!")
   	println(fr)
    
    // lambda functions:
    val mylambda: (String)->Unit = {s: String -> println(s) } 
   	val v:String = "Tutorials!!!"
   	mylambda(v)
    
    // passing lambda as parameter:
    myFun(v, mylambda) //passing lambda as a parameter of another function
    
    // destructuring declarations:
    val s = Student("Student John","Kotlin")
   	val (name, subject) = s
   	println("You are learning " + subject + " from " + name)
}

// standard function call:
fun MyFunction(x: String): String {
   	var c:String  = "Hey!! Welcome To ---"
   	return (c+x)
}

// passing lambda as parameter:
fun myFun(a: String, action: (String)->Unit) { //passing lambda 
   print("Heyyy!!!")
   action(a) // call to lambda function
}
*/
