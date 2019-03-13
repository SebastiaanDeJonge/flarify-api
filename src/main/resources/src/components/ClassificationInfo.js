import React, { Component } from 'react';

class ClassificationInfo extends Component {
    render() {
        return (
            <div className="widget">
                <h2>Classification</h2>
                <p>
                    The classification system for solar flares uses the letters A, B, C, M or X, according to
                    the peak flux in watts per square metre (W/m2) of X-rays with wavelengths 100 to 800
                    picometre, as measured at the Earth by the <a href="https://en.wikipedia.org/wiki/Geostationary_Operational_Environmental_Satellite">GOES</a> spacecraft.
                </p>
                <table>
                    <thead>
                    <tr>
                        <th>Classification</th>
                        <th>Peak flux range</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>A</td>
                        <td>&lt; 10<sup>-7</sup></td>
                    </tr>
                    <tr>
                        <td>B</td>
                        <td>10<sup>-7</sup> - 10<sup>-6</sup></td>
                    </tr>
                    <tr>
                        <td>C</td>
                        <td>10<sup>-6</sup> - 10<sup>-5</sup></td>
                    </tr>
                    <tr>
                        <td>M</td>
                        <td>10<sup>-5</sup> - 10<sup>-4</sup></td>
                    </tr>
                    <tr>
                        <td>X</td>
                        <td>&gt; 10<sup>-4</sup></td>
                    </tr>
                    </tbody>
                </table>
                <p>
                    <em>Source: <a href="https://en.wikipedia.org/wiki/Solar_flare">Wikipedia</a></em>
                </p>
            </div>
        )
    }
}

export default ClassificationInfo;
