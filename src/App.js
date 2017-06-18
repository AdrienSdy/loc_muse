import React from 'react';
import {AppRegistry, View} from 'react-native';
// import Header from './component/header/Header';
import Player from './component/Player';

class App extends React.Component {

  render() {
    return (
        <Player />
    );
  }
}

AppRegistry.registerComponent('loc_muse', () => App);