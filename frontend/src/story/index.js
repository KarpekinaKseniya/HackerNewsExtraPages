import {getStories} from "../service/BackendRequest";

export function createStoriesFunction() {
    return {
        stories: [],
        isLoading: false,
        err: null,

        fetchStories() {
            this.isLoading = true;
            try {
                getStories().then((json) => {
                    this.stories = json;
                    this.isLoading = false;
                });
            } catch (err) {
                this.error = err.message;
            }
        }
    };
}