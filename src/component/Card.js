var {StyleSheet} = require('react-native');

module.exports = StyleSheet.create({
    card: {
        backgroundColor: '#ffffff',
        borderRadius: 2,
        borderColor: '#ffffff',
        borderWidth: 1,
        shadowColor: 'rgba(0, 0, 0, 0.12)',
        shadowOpacity: 0.8,
        shadowRadius: 2,
        shadowOffset: {
            height: 1,
            width: 2,
        },
        width: '45%',
    },
    cardImage: {
        height: 170,
        resizeMode: 'cover',
    },
    cardTitle: {
        position: 'absolute',
        bottom: 0,
        left: 16,
        backgroundColor: 'transparent',
        padding: 16,
        fontSize: 24,
        color: '#fff',
    }
});