import React, { Component } from 'react';
import {
    View,
    Button
} from 'react-native';

import PlayerModule from '../module/Player';
import LocationModule from '../module/Location';

import { location } from '../data.json';

export default class Player extends Component {
    constructor(props) {
        super(props);

        this.getLocation = this.getLocation.bind(this);
        this.play = this.play.bind(this);
        this.pause = this.pause.bind(this);
    }

    play() {
        this.props.play();
    }

    pause() {
        this.props.pause();
    }

    getLocation() {
        this.props.getLocation();
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