import React from 'react';
import { 
  AppRegistry,
  View
} from 'react-native';

import PlayList from './component/PlayList';
import Player from './component/Player';
import PlayerModule from './module/Player';
import LocationModule from './module/Location';

import { location } from './data.json';

class App extends React.Component {

  constructor(props) {
    super(props);

    this.getLocation = this.getLocation.bind(this);
    this.selectMusic = this.selectMusic.bind(this);
    this.play = this.play.bind(this);

  }

  selectMusic(url) {
    PlayerModule.selectMusic(url, error => console.log(error));
    this.getLocation();
  }

  play() {
    PlayerModule.play();
    this.getLocation();
  }

  pause() {
    PlayerModule.pause();
  }

  getLocation() {
    const latitude_min = location.supdeweb.latitude - 0.0003;
    const latitude_max = location.supdeweb.latitude + 0.0003;
    const longitude_min = location.supdeweb.longitude - 0.002;
    const longitude_max = location.supdeweb.longitude + 0.002;

    LocationModule.getLocation((latitude, longitude) => {
      if(
          latitude > latitude_min 
          && latitude < latitude_max 
          && longitude > longitude_min
          && longitude < longitude_max
      ){
          this.pause();
      }
    });
  }

  render() {
    return (
      <View>
        <PlayList selectMusic={this.selectMusic} />
        <Player getLocation={this.getLocation} play={this.play} pause={this.pause} />
      </View>
    );
  }
}

AppRegistry.registerComponent('loc_muse', () => App);