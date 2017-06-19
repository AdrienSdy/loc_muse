import React, { Component } from 'react';
import {
    Text,
    View,
    Image,
    TouchableHighlight
} from 'react-native';

import PlayerModule from '../module/Player';

const Card = require('./Card');

export default class Music extends Component {
    constructor(props) {
        super(props);

        this.state = this.props.music;

        this.selectMusic = this.selectMusic.bind(this);
    }

    selectMusic() {
        this.props.selectMusic(this.state.url);
    }
    
    render() {
        const name = this.state.name;
        const img = this.state.img;

        return (
            <TouchableHighlight 
                style={Card.card}
                onPress={() => {this.selectMusic()}}
            >
                <View>
                    <Image
                        source={{uri: img}} 
                        style={Card.cardImage} 
                    />
                    <Text style={Card.cardTitle}>{name}</Text>
                </View>
            </TouchableHighlight>              
        );
    }
}