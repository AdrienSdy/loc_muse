import React, { Component } from 'react';
import {
    Text,
    View,
    Button,
    Image,
    TouchableHighlight
} from 'react-native';

import PlayerModule from '../module/Player';
import { musics } from '../data.json';
import Music from './Music';

const Card = require('./Card');

export default class PlayList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            musics: musics
        }

        this.selectMusic = this.selectMusic.bind(this);
    }
    
    selectMusic(url) {
        this.props.selectMusic(url);
    }

    render() {
        const musics = this.state.musics;
        return (
            <View>
                { musics.map((music, id) => (
                    <Music key={id} music={music} selectMusic={this.selectMusic}/>    
                ))}
            </View>
        );
    }
}