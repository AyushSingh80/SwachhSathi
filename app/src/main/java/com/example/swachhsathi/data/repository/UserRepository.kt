package com.example.swachhsathi.data.repository



import com.example.swachhsathi.data.model.User
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.coroutines.tasks.await
//
//class UserRepository {
//
////    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
////    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
//
//    /**
//     * Registers a new user.
//     * @param name User's name.
//     * @param email User's email.
//     * @param password User's password.
//     * @return A Result containing the User on success or an Exception on failure.
//     */
//    suspend fun registerUser(name: String, email: String, password: String): Result<User> {
//        return try {
//            // Create user with Firebase Authentication
//            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
//            val firebaseUser = authResult.user
//            if (firebaseUser != null) {
//                // Create User data object with a default role "Citizen"
//                val user = User(
//                    id = firebaseUser.uid,
//                    name = name,
//                    email = email,
//                    role = "Citizen"
//                )
//                // Store user data in Firestore
//                firestore.collection("users").document(firebaseUser.uid).set(user).await()
//                Result.success(user)
//            } else {
//                Result.failure(Exception("User registration failed: Firebase user is null."))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    /**
//     * Logs in an existing user.
//     * @param email User's email.
//     * @param password User's password.
//     * @return A Result containing the User on success or an Exception on failure.
//     */
//    suspend fun loginUser(email: String, password: String): Result<User> {
//        return try {
//            // Sign in with Firebase Authentication
//            val authResult = auth.signInWithEmailAndPassword(email, password).await()
//            val firebaseUser = authResult.user
//            if (firebaseUser != null) {
//                // Retrieve user data from Firestore
//                val snapshot = firestore.collection("users").document(firebaseUser.uid).get().await()
//                val user = snapshot.toObject(User::class.java)
//                if (user != null) {
//                    Result.success(user)
//                } else {
//                    Result.failure(Exception("User data not found in Firestore."))
//                }
//            } else {
//                Result.failure(Exception("Authentication failed: Firebase user is null."))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//}
class UserRepository {

    // Simulated local user database (in-memory storage)
    private val userDatabase = mutableMapOf<String, User>()
    private val loggedInUsers = mutableSetOf<String>()

    /**
     * Registers a new user.
     * @param name User's name.
     * @param email User's email.
     * @param password User's password.
     * @return A Result containing the User on success or an Exception on failure.
     */
    suspend fun registerUser(name: String, email: String, password: String): Result<User> {
        return try {
            if (userDatabase.containsKey(email)) {
                return Result.failure(Exception("User already exists with this email."))
            }
            // Create user with a default role "Citizen"
            val user = User(
                id = email,  // Using email as a unique ID
                name = name,
                email = email,
                role = "Citizen",
                password = password // Storing password for simplicity (not secure)
            )
            // Store user data in the local database
            userDatabase[email] = user
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Logs in an existing user.
     * @param email User's email.
     * @param password User's password.
     * @return A Result containing the User on success or an Exception on failure.
     */
    suspend fun loginUser(email: String, password: String): Result<User> {
        return try {
            val user = userDatabase[email]
            if (user != null && user.password == password) {
                loggedInUsers.add(email) // Mark user as logged in
                Result.success(user)
            } else {
                Result.failure(Exception("Invalid email or password."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Logs out a user.
     * @param email User's email.
     */
    fun logoutUser(email: String) {
        loggedInUsers.remove(email)
    }

    /**
     * Checks if a user is logged in.
     * @param email User's email.
     * @return `true` if logged in, `false` otherwise.
     */
    fun isUserLoggedIn(email: String): Boolean {
        return loggedInUsers.contains(email)
    }
}