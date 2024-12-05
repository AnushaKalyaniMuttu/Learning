// Filename - App.js

import React from "react";
class App extends React.Component {
    constructor(props) {
        super(props);

        // Initializing the state
        this.state = { color: "lightgreen" };
    }
    componentDidMount() {
        // Changing the state after 2 sec
        // from the time when the component
        // is rendered
        setTimeout(() => {
            this.setState({ color: "wheat" });
        }, 2000);
    }
    render() {
        return (
            <div>
                <p
                    style={{
                        color: this.state.color,
                        backgroundColor: "rgba(0,0,0,0.88)",
                        textAlign: "center",
                        paddingTop: 20,
                        width: 400,
                        height: 80,
                        margin: "auto"
                    }}
                >
                    GeeksForGeeks
                </p>
            </div>
        );
    }
}
export default App;



/*

Summary
componentDidMount is a React lifecycle method in class components, called once after a component mounts to the DOM. It’s used for tasks like fetching data, setting up subscriptions, or manipulating the DOM, ensuring these actions occur post-render.
*/
