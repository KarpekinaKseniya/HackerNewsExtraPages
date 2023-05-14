import {getStories, getStory} from "../service/BackendRequest";

export function createStoriesFunction() {
    return {
        stories: [],
        story: {},
        isLoading: false,

        fetchStories() {
            this.isLoading = true;
            try {
                getStories().then((json) => {
                    this.stories = json;
                    this.isLoading = false;
                });
            } catch (err) {
                console.log(err.message);
            }
        },

        fetchStory(id) {;
            try {
                getStory(id).then((json) => {
                    this.story = json;
                });
            } catch (err) {
                console.log(err.message);
            }
        }
    };
}