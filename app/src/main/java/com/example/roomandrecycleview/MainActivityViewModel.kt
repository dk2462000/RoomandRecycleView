package com.example.roomandrecycleview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.roomandrecycleview.db.RoomAppDb
import com.example.roomandrecycleview.db.UserEntity

class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    var allUsers : MutableLiveData<List<UserEntity>> = MutableLiveData()

    init{
        getAllUsers()
    }

    fun getAllUsersObservers(): MutableLiveData<List<UserEntity>> {
        return allUsers
    }

    fun getAllUsers() {
        val userDao = RoomAppDb.getAppDatabase((getApplication()))?.userDao()
        val list = userDao?.getAllUserInfo()

        allUsers.postValue(list)
    }

    fun insertUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.userDao()
        userDao?.insertUser(entity)
        getAllUsers()
    }

    fun updateUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.userDao()
        userDao?.updateUser(entity)
        getAllUsers()
    }

    fun deleteUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.userDao()
        userDao?.deleteUser(entity)
        getAllUsers()
    }
}