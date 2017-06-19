import React from 'react';
import { 
  AppRegistry,
  View
} from 'react-native';
// import Header from './component/header/Header';
import PlayList from './component/PlayList';
import Player from './component/Player';
import PlayerModule from './module/Player';

class App extends React.Component {


  play() {
    PlayerModule.play();
  }

  pause() {
    PlayerModule.pause(error => console.log(error));
  }

  selectMusic(url) {
    PlayerModule.selectMusic(url, error => console.log(error));
  }

  render() {
    return (
      <View>
        <PlayList selectMusic={this.selectMusic} />
        <Player play={this.play} pause={this.pause} />
      </View>
    );
  }
}

AppRegistry.registerComponent('loc_muse', () => App);