package com.imrkjoseph.squadzipexam.main

import com.imrkjoseph.squadzipexam.app.foundation.BaseActivity
import com.imrkjoseph.squadzipexam.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenHandler : BaseActivity<ActivityMainBinding>(bindingInflater = ActivityMainBinding::inflate)