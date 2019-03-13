import React, { Component } from 'react';

class Summary extends Component {

    constructor(props) {
        super(props);

        // @todo Validate summary and set hasResults based on that
        this.state = {
            hasResults: true,
            summary: props.summary
        }
    }

    componentWillReceiveProps(nextProps, nextContext) {

        this.setState({
            hasResults: true,
            summary: nextProps.summary
        });
    }

    render() {
        if (this.state.hasResults) {
            return (

                <div className="widget">
                    <p>
                        Between <strong>{this.state.summary.startDate}</strong> and <strong>{this.state.summary.endDate}</strong> there were <strong>{this.state.summary.totalSolarFlares}</strong> solar flares registered.
                    </p>
                    <p>
                        The strongest solar flare measured was of class <strong>{this.state.summary.mostPowerful.classType}</strong> with a peak flux at <strong>{this.state.summary.mostPowerfulPicometre}</strong> picometre (watts/m<sup>2</sup>) and occurred on <strong>{this.state.summary.mostPowerful.beginTime}</strong>.
                    </p>
                    <p> Most solar flares were measured at region <strong>{this.state.summary.mostActiveRegion.region}</strong>, with a total of <strong>{this.state.summary.mostActiveRegion.totalSolarFlares}</strong> flares.
                </p>
                </div>
            );
        } else {
            return (
                <div className="widget">
                    No results were found that match your request :-(
                </div>
            )
        }
    }
}

export default Summary;
