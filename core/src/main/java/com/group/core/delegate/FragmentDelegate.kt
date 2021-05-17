//package com.group.core.delegate
//
//import android.os.Bundle
//import android.os.Parcelable
//import androidx.fragment.app.Fragment
//import java.io.Serializable
//import kotlin.properties.ReadWriteProperty
//import kotlin.reflect.KProperty
//
//class FragmentNullableArgumentDelegate<T : Any?> :
//    ReadWriteProperty<Fragment, T?> {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun getValue(
//        thisRef: Fragment,
//        property: KProperty<*>
//    ): T? {
//        val key = property.name
//        return thisRef.arguments?.get(key) as? T
//    }
//
//    override fun setValue(
//        thisRef: Fragment,
//        property: KProperty<*>, value: T?
//    ) {
//        val args = thisRef.arguments
//            ?: Bundle().also(thisRef::setArguments)
//        val key = property.name
//        value?.let { args.put(key, it) } ?: args.remove(key)
//    }
//}
//
//fun <T : Any> argumentNullable(): ReadWriteProperty<Fragment, T?> =
//    FragmentNullableArgumentDelegate()
//
//
