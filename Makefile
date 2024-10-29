.PHONY: init push-test delete-test

init:
	git remote add private https://sdas1231@github.com:sdas1231/pair-programming-test-private

push-test:
	git checkout -b car-api && git pull private car-api --rebase && git push origin car-api

delete-test:
	git reset --hard && git checkout master && git branch -D car-api && git push -d origin car-api
