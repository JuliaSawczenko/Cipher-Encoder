package chucknorris
    var result: MutableList<Any> = mutableListOf()

    fun partition(list: MutableList<Any>, size: Int): MutableList<List<Any>> {
        var partitions: MutableList<List<Any>> = mutableListOf()
        if (list.isEmpty()){
            return partitions
        }

        var length = list.size
        var numOfPartitions: Int
        if (length % size == 0){
             numOfPartitions = length / size +  0
            } else { numOfPartitions = length / size +  1
        }

        for (i in 0 until numOfPartitions) {
            val from = i * size
            val to = Math.min(i * size + size, length)
            partitions += (list.subList(from, to))
        }
        return partitions
    }


    fun separate (array: MutableList<String>){
        while (array.size > 1){
            if(array.size % 2 != 0){
                throw Exception("not valid")
            }

            var string = array[1]

            if (array[0] == "0"){
                for (i in string){
                result.add(1)
                }
            } else if (array[0] == "00"){
                for (i in string){
                    result.add(0)
                }
            } else {
                throw Exception("not valid")
            }
            if (array.size >= 1){
                array.removeAt(1)
            }
            if (array.size >= 0){
                array.removeAt(0)
            }
            separate(array)
        }
    }

    fun decode(string: String): String {
        var array = string.split(" ").toMutableList()
        separate(array)
        if (result.size % 7 != 0){
            throw Exception("7")
        }
        var subSets: MutableList<List<Any>> = partition(result, 7)
        var array_str: MutableList<String> = mutableListOf()
        var numb: String = ""
        var index = 0

         for(list in subSets){
            numb = ""
            for (integer in list){
                numb += integer
            }
            array_str += numb;
            index++
        }

        var array_char: MutableList<Char> = mutableListOf()
        for (i in 0 until array_str.size){
            var j: Int = Integer.parseInt(array_str[i], 2)
            array_char += j.toChar()
        }
        result.clear()
        return array_char.joinToString("")
    }

    fun divide(string: String){
    if (string.isNotEmpty()&& string != ""){
        var substring_same : String = string[0].toString()
        var divided = ""
        for (i in 1 until string.length){
            val previous = i - 1
            
            if (string[previous] == string[i]){
                substring_same += string[i]
            } else if (string[previous] != string[i]){
                divided = string.substring(i)
                break
            }
        }
        if (substring_same.isNotEmpty()) {
            encode (substring_same)
        }
        substring_same = ""
        var liczba = divided.length
        while (liczba > 0){
            substring_same = divided[0].toString()
            liczba--
            for ( i in 1 until divided.length){
                val previous = i - 1
                if (divided[previous] == divided.get(i)){
                    substring_same += divided[i]
                    liczba--
                } else if (divided[previous] != divided[i]){
                    divided = divided.substring(i)
                    break
                }
            }

            if (substring_same.isNotEmpty()){

                encode(substring_same)
            }
            substring_same = ""
            substring_same = divided[0].toString()
        }
    } else {
        println("error")
    }
}

fun encode(substring: String){
    if (substring[0] == '0'){
        print(" 00 ")
    } else if (substring[0] == '1'){
        print(" 0 ")
    }
    for (i in substring){
        print("0")
    }
}



fun main() {
    var operation: String
    do{
        println("Please input operation (encode/decode/exit):")
        operation = readln()
        when (operation){
            "encode" ->{
                println("Input string:")
                var word = readln()
                println("Encoded string:")
                var whole = ""
                for (ch in 0 until word.length){
                    var binary = Integer.toBinaryString(word[ch].code)
                    if (binary.length != 7){
                        binary = "0$binary"
                    }
                    whole += binary
                }

                divide(whole)
                println()
        }
            "decode" -> {
                println("Input encoded string:")
                var word = readln()
                var result = ""
                val regex = "^[0 ]+$".toRegex()
                try {
                    if (!regex.containsMatchIn(word)) {
                        println("Encoded string is not valid.")
                        println()
                    } else {
                        result = decode(word)
                        println("Decoded string:")
                        println(result)
                        println()
                    }
                } catch (e: Exception) {
                    println("Encoded string is not valid.")
                    println()
                }
            }
                "exit" -> break
            else ->{
                println("There is no '$operation' operation")
                println()
            }
        }
    } while (!operation.equals("exit"))
    println("Bye!")


}