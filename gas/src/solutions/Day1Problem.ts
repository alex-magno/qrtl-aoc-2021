namespace challenges {

    export class day1Problem {

        data: string[];

        constructor(dataUrl: string) {
            this.data = this.getData(dataUrl);
        }

        solve(): Solution {
            const partOneResult = this.solvePartOne(this.data);
            const partTwoResult = this.solvePartTwo(this.data);
            return {
                day: 'd1',
                partOneResult: `<p>Part One result: ${partOneResult}</p>`,
                partTwoResult: `<p>Part Two result: ${partTwoResult}</p>`
            }
        }

        protected solvePartOne(data: any[]): number {
            let counter = 0;
            for (let i = 0; i < data.length; i++) {
                if (+data[i] > +data[i - 1]) {
                    counter++;
                }
            }
            return counter;
        }

        protected solvePartTwo(data: any[]): number {
            let newData: any[] = [];
            for (let i = 0; i < data.length; i++) {
                const [a, b, c]: number[] = [+data[i], +data[i + 1], +data[i + 2]];
                newData.push(a + b + c);
            }
            return this.solvePartOne(newData);
        }

        private getData(dataUrl: string): string[] {
            const response = UrlFetchApp.fetch(dataUrl);
            return response.getContentText().split('\n');
        }
    }
}
