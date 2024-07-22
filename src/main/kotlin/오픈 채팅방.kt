fun main() {
    val solution = 오픈_채팅방()
    val message = solution.solution(
        arrayOf(
            "Enter uid1234 Muzi",
            "Enter uid4567 Prodo",
            "Leave uid1234",
            "Enter uid1234 Prodo",
            "Change uid4567 Ryan"
        )
    )
    println(message.asList())
}

private class 오픈_채팅방 {
    fun solution(record: Array<String>): Array<String> {
        val users = getUsers(record)
        return convertMessage(record, users)
    }

    fun convertMessage(record: Array<String>, users: Map<String, String>): Array<String> {
        return record
            .asList()
            .map { message -> splitMessage(message) }
            .filter { tripleMessage -> tripleMessage.first != "Change" }
            .map { tripleMessage -> "${users[tripleMessage.second]}님이 ${actionMessage(tripleMessage.first)}" }
            .toTypedArray()
    }

    fun getUsers(record: Array<String>): Map<String, String> {
        val users = HashMap<String, String>()
        record
            .asList()
            .map { message -> splitMessage(message) }
            .filter { tripleMessage -> tripleMessage.first != "Leave" }
            .forEach { tripleMessage ->
                users.put(tripleMessage.second, tripleMessage.third)
            }
        return users
    }

    fun splitMessage(message: String): Triple<String, String, String> {
        val splitMessage = message.split(" ")
        val action = splitMessage[0]
        val id = splitMessage[1]

        if (id.isEmpty() || id.length > 10) {
            throw Exception()
        }

        var nickname = ""
        if (splitMessage.size == 3) {
            nickname = splitMessage[2]
            if (nickname.isEmpty() || nickname.length > 10) {
                throw Exception()
            }
        }

        return Triple(action, id, nickname)
    }

    fun actionMessage(action: String): String {
        return if (action == "Enter") {
            "들어왔습니다."
        } else {
            "나갔습니다."
        }
    }
}