import React, { Component } from 'react';

class Footer extends Component {

    render() {
        return (
            <div className="widget">
                This application is meant for non-commercial demonstration purposes only. Real solar flare data provided freely by
                <a href="https://api.nasa.gov/">NASA</a>.<br />
                Created by <a href="https://github.com/SebastiaanDeJonge">Sebastiaan de Jonge</a>. View project source code on <a href="https://github.com/SebastiaanDeJonge/spring_flarify">GitHub</a>.
            </div>
        );
    }
}

export default Footer;
