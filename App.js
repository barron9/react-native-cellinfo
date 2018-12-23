/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View,FlatList,ProgressBarAndroid,Dimensions} from 'react-native';
import moduleXX from './CustomModule.js';
import * as Progress from 'react-native-progress';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App extends Component<Props> {
  constructor(props){
    super(props)
    this.state=[{data:false}]
  }
  async  componentDidMount(){

  //alert( moduleXX.show())
//await alert(JSON.stringify(moduleXX.show()))


this.newfunc()

  }

  newfunc(){

    var {data} = await moduleXX.show()
alert(data)
var data0=data.split(" ")
var data2 = new Array();

for(var i=0; i<data0.length;i++){
  
  data2.push({key : data0[i]})
  }
  this.setState({data:data2})

  }
 
  render() {
    return (
      <View style={styles.container}>
      <Text style={{margin:10,fontSize:24,fontWeight:'900'}}>Neighbour Cells (RSSI/SEC)</Text> 
      
        {this.state.data&&this.state.data.length>0&&
        <FlatList
  data={this.state.data}  
  renderItem={({item}) => 
  <View style={{flexDirection:'row',marginLeft:10,alignItems:'center'}}>
    <Text style={{fontWeight:'100'}}>RSSI {item.key?(parseInt(item.key)):'' }</Text>
    <Progress.Bar progress={item.key?(150+parseInt(item.key))/100:.22  } indeterminate={!item.key} width={200} 
    style={{margin:10}} />
  </View> }
/>}
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
   width:Dimensions.get('window').width-20,

 },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
