import { StyleSheet, Text, View } from 'react-native'
import React ,{useEffect}from 'react'
import {NetworkInfo} from 'react-native-network-info';
import NetInfo from '@react-native-community/netinfo';
import {NativeModules} from 'react-native';
import CustomModule from './native'
const App = () => {
  useEffect(() => {
    getNetworkBandwidth();
  },[])


  
CustomModule.getSpeed((maxDownloadSpeed, maxUploadSpeed) => {
  if (maxDownloadSpeed !== undefined && maxUploadSpeed !== undefined) {
    // Handle the data received from the native module
    console.log('Max Download Speed:', maxDownloadSpeed);
    console.log('Max Upload Speed:', maxUploadSpeed);
  } else {
    // Handle the case where no data is returned
    console.log('Not connected to mobile data or no network connection.');
  }
});


  const getNetworkBandwidth =()=> {
  //  let arr = CustomModule.getSpeed();
  //  console.log(CustomModule.getSpeed());
  }
  return (
    <View>
      <Text>App</Text>
    </View>
  )
}

export default App

const styles = StyleSheet.create({})


  