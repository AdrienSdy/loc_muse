import React, { Component } from 'react';
import {
    View,
    Button
} from 'react-native';

import PlayerModule from '../module/Player';

export default class Player extends Component {
    constructor(props) {
        super(props);
    }
    
    play() {
        PlayerModule.play(error => console.log(error));
    }

    pause() {
        PlayerModule.pause(error => console.log(error));
    }
    
    render() {
        return (
            <View style={{width: '50%', height: 50, justifyContent:'center'}}>
                <Button color='#262324' onPress={this.play} title="Play"></Button>
                <Button color='#262324' onPress={this.pause} title="Pause"></Button>
            </View>
        );
    }
}